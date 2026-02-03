import { useNavigate } from "react-router-dom";
import { useState } from "react";
import "../css/Navbar.css";

function Navbar() {
  const navigate = useNavigate();
  const [open, setOpen] = useState(false);

  const logout = () => {
    localStorage.removeItem("token");
    navigate("/login");
  };

  return (
    <nav className="navbar">
      <div className="navLeft" onClick={() => navigate("/dash")}>
        StudyTracker
      </div>

      <div className="navRight">
        <button className="navBtn" onClick={() => navigate("/dash")}>
          Dashboard
        </button>
        <div
          className="dropdown"
          onMouseEnter={() => setOpen(true)}
          onMouseLeave={() => setOpen(false)}
        >
          <button className="dropBtn">Study Logs ▾</button>

          {open && (
            <div className="dropdownMenu">
              <div onClick={() => navigate("/study/add")}>➕ Add Study Log</div>
              <div onClick={() => navigate("/study/view")}>📄 View Study Logs</div>
            </div>
          )}
        </div>

        <button className="logoutBtn" onClick={logout}>
          Logout
        </button>
      </div>
    </nav>
  );
}

export default Navbar;