import { useState } from "react";
import { useNavigate } from "react-router-dom";
import "../css/Login.css";
function Login(){
    const[email,setEmail]=useState("");
    const[password,setPassword]=useState("");
    const navigate=useNavigate();
    const handleRequest=async()=>{
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
        <div className="loginContainer">
            <div className="loginCard">
                <h1>Login</h1>
            <input type="email"  placeholder="Enter Email" value={email} onChange={(e)=>setEmail(e.target.value)}/>
            <input type="password" placeholder="Enter Password" value={password} onChange={(e)=>setPassword(e.target.value)}/>
            <button onClick={handleRequest}>Login</button>
            <p>If u havent <a href="/register">Register</a></p>
            </div>
        </div>
    )
}

export default Login;