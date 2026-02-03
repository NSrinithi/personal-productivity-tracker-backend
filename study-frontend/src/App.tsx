import { Navigate, Route, Routes } from "react-router-dom";
import Login from "./auth/Login"
import Register from "./auth/Register";
import DashBoard from "./pages/DashBoard";
import StudyLogAdd from "./pages/StudyLogAdd";
import StudyLogView from "./pages/StudyLogView";
import DailySummary from "./pages/DailySummary";

function App() {
  return(
    <div>
      <Routes>
        <Route path="/" element={<Navigate to="/login" />} />
        <Route path="/login" element={<Login/>} />
        <Route path="/dash" element={<DashBoard/>}/>
        <Route path="/register" element={<Register/>} />
        <Route path="/study/add" element={<StudyLogAdd/>}/>
        <Route path="/study/view" element={<StudyLogView/>}/>
        <Route path="/study/add/:id" element={<StudyLogAdd/>}/>
        <Route path="/dailySummary" element={<DailySummary/>}/>
      </Routes>
    </div>
  )
}

export default App;
