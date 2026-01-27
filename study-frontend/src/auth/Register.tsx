import { useState } from "react"
import { useNavigate } from "react-router-dom";

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
        <div>
            <h1>Register</h1>
            <input type="email" placeholder="Enter Email" value={email}  onChange={(e)=>setEmail(e.target.value)}/>
            <br />
            <input type="password" placeholder="Enter Password" value={password} onChange={(e)=>setPassword(e.target.value)} />
            <br />
            <button onClick={handleRequest}>Register</button>
        </div>
    )
}
export default Register;