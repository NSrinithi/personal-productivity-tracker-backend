import { useEffect, useState } from "react";
import { useNavigate, useParams } from "react-router-dom";
import "../css/StudyLogAdd.css";
import Navbar from "./NavBar";
import { apiFetch } from "../utils/apiFetch";

type StudyLog = {
    date: string;
    topic: string;
    category: string;
    hours: number;
    notes?: string;
};

function StudyLogAdd() {
    const [studyLog, setStudyLog] = useState<StudyLog>({
        date: "",
        topic: "",
        category: "",
        hours: 0,
        notes: ""
    });

    const[error,setErrors]=useState<{date?:string,topic?:string,category?:string}>({});

    const { id } = useParams();
    const isEdit = Boolean(id);
    const navigate = useNavigate();


    const validate=()=>{
        const newErrors:{date?:string,topic?:string,category?:string}={};

        if(!studyLog.date){
            newErrors.date="Date is required";
        }
        if(!studyLog.topic.trim()){
            newErrors.topic="Topic required";
        }
        if(!studyLog.category.trim()){
            newErrors.category="Category required";
        }

        setErrors(newErrors);
        return Object.keys(newErrors).length===0

    }
    useEffect(() => {
        const token=localStorage.getItem("token");
            if (!token) {
                navigate("/login");
                return;
            }

        if (!id) return;
        const editRequest = async () => {
            
            const res = await fetch(`http://localhost:8080/study/${id}`, {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("token")
                }
            });

            if (res.ok) {
                const json = await res.json();
                setStudyLog(json);
            }
        };

        editRequest();
    }, [id]);

    const handleRequest = async () => {
        if(!validate()) return;
        try {
            const url = isEdit
            ? `http://localhost:8080/study/${id}`
            : "http://localhost:8080/study";

        const res = await apiFetch(url, {
            method: isEdit ? "PUT" : "POST",
            headers: {
                "Content-Type": "application/json",
                Authorization: "Bearer " + localStorage.getItem("token")
            },
            body: JSON.stringify(studyLog)
        },navigate);
        alert(isEdit ? "StudyLog updated successfully" : "StudyLog added successfully");
        navigate("/study/view");
        } catch (error:any) {
            alert(error.message);
        }
        
    };

    const today = new Date().toISOString().split("T")[0];

    return (
        <>
        <Navbar/>
        <div className="studyFormContainer">
            <div className="studyFormCard">
                <h1>{isEdit ? "Update Study Log" : "Add Study Log"}</h1>

                <input
                    type="date"
                    max={today}
                    value={studyLog.date}
                    onChange={(e) => setStudyLog({ ...studyLog, date: e.target.value })}
                />
                {error.date && <p className="errorText">{error.date}</p>}
                <input
                    type="text"
                    placeholder="Topic"
                    value={studyLog.topic}
                    onChange={(e) => setStudyLog({ ...studyLog, topic: e.target.value })}
                />
                {error.topic && <p className="errorText">{error.topic}</p>}

                <input
                    type="text"
                    placeholder="Category"
                    value={studyLog.category}
                    onChange={(e) => setStudyLog({ ...studyLog, category: e.target.value })}
                />
                {error.category && <p className="errorText">{error.category}</p>}

                <input
                    type="number"
                    placeholder="Hours"
                    min={0}
                    value={studyLog.hours}
                    onChange={(e) => setStudyLog({ ...studyLog, hours: Number(e.target.value) })}
                />

                <input
                    type="text"
                    placeholder="Notes (optional)"
                    value={studyLog.notes}
                    onChange={(e) => setStudyLog({ ...studyLog, notes: e.target.value })}
                />

                <button onClick={handleRequest}>
                    {isEdit ? "Update" : "Add"}
                </button>
            </div>
        </div>
        </>
    );
}

export default StudyLogAdd;