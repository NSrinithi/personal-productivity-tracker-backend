import { Navigate, Route, Routes } from "react-router-dom";
import Login from "./auth/Login"
import Register from "./auth/Register";
import DashBoard from "./pages/DashBoard";

function App() {
  return(
    <div>
      <Routes>
        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="/login" element={<Login/>} />
        <Route path="/dash" element={<DashBoard/>}/>
        <Route path="/register" element={<Register/>} />
        
      </Routes>
    </div>
  )
}

export default App;
