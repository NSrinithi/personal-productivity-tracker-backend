import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../css/StudyLogView.css";
import Navbar from "./NavBar";
import { apiFetch } from "../utils/apiFetch";
function StudyLogView(){
    type StudyView={
        id:number;
        category:string;
        date:string;
        hours:number;
        topic:string;
        notes?:string;
    }

    const navigate=useNavigate();

    const[data,setdata]=useState<StudyView[]>([]);
    const[page,setPage]=useState(0);
    const[totalPage,setTotalPage]=useState(0);


    const fetchData=async(page: number)=>{
            const token=localStorage.getItem("token");
            if (!token) {
                navigate("/login");
                return;
            }
            console.log("Token: "+token);
            const res=await fetch(`http://localhost:8080/study/page?page=${page}&size=5`,{
                method:"GET",
                headers:{
                    Authorization: "Bearer "+token
                }
            })

            const json=await res.json();
            console.log(json);
            setdata(json.content);
            setPage(json.number);
            setTotalPage(json.totalPages);
        }
    useEffect(()=>{
        fetchData(page);
    },[page])

    
    
    const deleteRequest=async(id:number)=>{
        try {
            const ref=await apiFetch("http://localhost:8080/study/"+id,{
            method:"DELETE",
            headers:{
                Authorization: "Bearer "+localStorage.getItem("token")
            }
        },navigate)
        alert("Deleted successfully");
        fetchData(page);
        } catch (error:any) {
            alert(error.message)
        }
    }

    return(
        <>
        <Navbar/>
        <div className="studyViewContainer">
  <h1>Study Logs</h1>

  {data.length === 0 && <p className="emptyText">No study logs</p>}

  {data.map((log) => (
    <div className="studyLogCard" key={log.id}>
      <p><b>Date:</b> {log.date}</p>
      <p><b>Topic:</b> {log.topic}</p>
      <p><b>Category:</b> {log.category}</p>
      <p><b>Hours:</b> {log.hours}</p>
      {log.notes && <p><b>Notes:</b> {log.notes}</p>}

      <div className="actionButtons">
        <button className="editBtn" onClick={() => navigate("/study/add/" + log.id)}>
          Edit 📝
        </button>
        <button className="deleteBtn" onClick={() => deleteRequest(log.id)}>
          Delete ❌
        </button>
      </div>
    </div>
  ))}
  <button disabled={page===0} onClick={()=> setPage(page-1)}>Previous</button>
  <span> Page {page + 1} of {totalPage} </span>
  <button disabled={page+1>=totalPage} onClick={()=> setPage(page+1)}>Next</button>
</div>
</>
    )
}
export default StudyLogView;