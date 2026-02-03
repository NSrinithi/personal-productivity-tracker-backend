import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import "../css/DashBoard.css";
import Navbar from "./NavBar";
import { apiFetch } from "../utils/apiFetch";


type WeeklyStats = {
  totalHours: number;
  studiedDays: number;
  missedDays: number;
};

type MonthlyStats = {
  totalHours: number;
  studiedDays: number;
  missedDays: number;
  averageHoursPer: number;
};

type DashBoardData = {
  today: string;
  isStudied: boolean;
  totalHours: number;
  currentStreak: number;
  longestStreak: number;
  lastStudiedDate: string;
  Categories: Record<string, number>;
  weekly: WeeklyStats;
  monthly: MonthlyStats;
};

/* ---------- Component ---------- */

function DashBoard() {
  const [data, setData] = useState<DashBoardData | null>(null);
  const[loading,setLoading]=useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchDashboard = async () => {
      const token = localStorage.getItem("token");

      if (!token) {
        navigate("/login");
        return;
      }

      try {
      const res = await apiFetch(
        "http://localhost:8080/study/dash",
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        },
        navigate
      );

      const json = await res.json();   
      setData(json);                   
    } catch (error: any) {
      console.error(error.message);    
    } finally {
      setLoading(false);
    }
    };

    fetchDashboard();
  }, [navigate]);


  if (loading) {
    return <div className="loading">Loading dashboard...</div>;
  }

  if(!data){
    return(
      <div className="dashboardContainer">
      <h1>Dashboard</h1>

      <div className="card">
        <h3>No study data yet 📭</h3>
        <p>Start by adding your first study log.</p>

        <button className="addBtn" onClick={() => navigate("/study/add")}>
          Add Study Log
        </button>
      </div>
    </div>
    )
  }

  return (
    <>
    <Navbar/>
    <div className="dashboardContainer">
      {/* Header */}
      <div className="dashboardHeader">
        <h1>Dashboard</h1>
        <button className="addBtn" onClick={() => navigate("/study/add")}>
          Add Study Log
        </button>
      </div>

      {/* Top Cards */}
      <div className="cardGrid">
        <div className="card">
          <h3>Today</h3>
          <p>{data.today}</p>
        </div>

        <div className="card">
          <h3>Studied Today</h3>
          <p>{data.isStudied ? "Yes" : "No"}</p>
        </div>

        <div className="card">
          <h3>Hours Today</h3>
          <p>{data.totalHours} hrs</p>
        </div>

        <div className="card">
          <h3>Current Streak</h3>
          <p className="streak">🔥 {data.currentStreak}</p>
        </div>

        <div className="card">
          <h3>Longest Streak</h3>
          <p>🏆 {data.longestStreak}</p>
        </div>

        <div className="card">
          <h3>Last Studied</h3>
          <p>{data.lastStudiedDate}</p>
        </div>
      </div>

      {/* Categories */}
      <div className="section">
        <h2 className="sectionTitle">Category Breakdown</h2>

        {Object.keys(data.Categories).length === 0 ? (
          <p>No categories recorded yet.</p>
        ) : (
          <ul className="categoryList">
            {Object.entries(data.Categories).map(([cat, hrs]) => (
              <li key={cat}>
                <span>{cat}</span>
                <span>{hrs} hrs</span>
              </li>
            ))}
          </ul>
        )}
      </div>

      {/* Weekly */}
      <div className="section">
        <h2 className="sectionTitle">Weekly Stats</h2>
        <div className="card">
          <div className="statRow">
            <span>Total Hours</span>
            <span>{data.weekly.totalHours} hrs</span>
          </div>
          <div className="statRow">
            <span>Studied Days</span>
            <span>{data.weekly.studiedDays}</span>
          </div>
          <div className="statRow">
            <span>Missed Days</span>
            <span>{data.weekly.missedDays}</span>
          </div>
        </div>
      </div>

      {/* Monthly */}
      <div className="section">
        <h2 className="sectionTitle">Monthly Stats</h2>
        <div className="card">
          <div className="statRow">
            <span>Total Hours</span>
            <span>{data.monthly.totalHours} hrs</span>
          </div>
          <div className="statRow">
            <span>Studied Days</span>
            <span>{data.monthly.studiedDays}</span>
          </div>
          <div className="statRow">
            <span>Missed Days</span>
            <span>{data.monthly.missedDays}</span>
          </div>
          <div className="statRow">
            <span>Avg / Day</span>
            <span>{data.monthly.averageHoursPer} hrs</span>
          </div>
        </div>
      </div>
    </div>
    </>
  );
  
}

export default DashBoard;