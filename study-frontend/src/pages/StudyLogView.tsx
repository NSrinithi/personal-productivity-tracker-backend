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
    const fetchData=async()=>{
            const token=localStorage.getItem("token");
            if (!token) {
                navigate("/login");
                return;
            }
            console.log("Token: "+token);
            const res=await fetch("http://localhost:8080/study/user",{
                method:"GET",
                headers:{
                    Authorization: "Bearer "+token
                }
            })

            const json=await res.json();
            console.log(json);
            setdata(json);
        }
    useEffect(()=>{
        fetchData();
    },[])

    
    const deleteRequest=async(id:number)=>{
        try {
            const ref=await apiFetch("http://localhost:8080/study/"+id,{
            method:"DELETE",
            headers:{
                Authorization: "Bearer "+localStorage.getItem("token")
            }
        },navigate)
        alert("Deleted successfully");
        fetchData();
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
</div>
</>
    )
}
export default StudyLogView;