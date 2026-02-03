import { useState } from "react"
import { useNavigate } from "react-router-dom";
import "../css/Register.css";
function Register(){
    const[email,setEmail]=useState("");
    const[password,setPassword]=useState("");
    const navigate=useNavigate();
    const handleRequest=async()=>{
        console.log("hii");
        const res=await fetch("http://localhost:8080/user/register",{
            method:"POST",
            headers:{"Content-Type":"application/json"},
            body:JSON.stringify({
                email,
                password
            })
        });

        if(res.ok){
            alert("Register successfully");
            navigate("/login");
        }
        else{
            alert("Register failed");
        }
    }

    return(
        <div className="registerContainer">
            <div className="registerCard">
            <h1>Register</h1>
            <input type="email" placeholder="Enter Email" value={email}  onChange={(e)=>setEmail(e.target.value)}/>
            <input type="password" placeholder="Enter Password" value={password} onChange={(e)=>setPassword(e.target.value)} />
            <button onClick={handleRequest}>Register</button>
            </div>
        </div>
    )
}
export default Register;