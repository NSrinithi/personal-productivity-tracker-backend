import { useState } from "react";
import { useNavigate } from "react-router-dom";

function Login(){
    const[email,setEmail]=useState("");
    const[password,setPassword]=useState("");
    const navigate=useNavigate();
    const handleRequest=async()=>{
        console.log("hi");
        const res=await fetch("http://localhost:8080/user/login",{
            method:"POST",
            headers:{"Content-type":"application/json"},
            body:JSON.stringify({
                email,
                password
            })
        })
        if(res.ok){
            console.log("Status:", res.status);
            const token=await res.text();
            console.log("Token:"+token);
            localStorage.setItem("token",token);
            alert("Login successfull");
            navigate("/dash");
        }
        else{
            alert("Login failed");
        }
    }
    return(
        <div>
            <h1>Login</h1>
            <input type="email"  placeholder="Enter Email" value={email} onChange={(e)=>setEmail(e.target.value)}/>
            <br />
            <input type="password" placeholder="Enter Password" value={password} onChange={(e)=>setPassword(e.target.value)}/>
            <br />
            <button onClick={handleRequest}>Login</button>
        </div>
    )
}

export default Login;