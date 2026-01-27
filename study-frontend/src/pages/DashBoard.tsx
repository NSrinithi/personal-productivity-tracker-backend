import { useEffect, useState } from "react";

function DashBoard(){
type DashBoard = {
  today: string;
  isStudied: boolean;
  totalHours: number;
  currentStreak: number;
  longestStreak: number;
  lastStudiedDate: string;
  
};

    const [data, setData] = useState<DashBoard | null>(null);
    useEffect(()=>{
        const fetchData=async()=>{
            const token=localStorage.getItem("token");
            console.log("Token: "+token);
            const res=await fetch("http://localhost:8080/study/dash",{
                headers:{
                    Authorization: "Bearer "+token
                }
            })

            const json=await res.json();
            console.log(json);
            setData(json);
        }
        fetchData();
    },[])

    if(!data){
        <p>Loading....</p>
    }
    return(
        <div>
            <h1>DashBoard</h1>
            <p>Today: {data?.today}</p>
           <p>Studied today: {data?.isStudied ? "Yes" : "No"}</p>
            <p>Hours today: {data?.totalHours}</p>
           <p>🔥 Current streak: {data?.currentStreak}</p>
             <p>🏆 Longest streak: {data?.longestStreak}</p>
             <p>Last studied: {data?.lastStudiedDate}</p> 
        </div>
    )
}
export default DashBoard;