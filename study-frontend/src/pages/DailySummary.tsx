import { useEffect, useState } from "react";
import "../css/DailySummary.css";

type Daily = {
  categories: Record<string, number>;
  date: string;
  isStudied: boolean;
  totalHours: number;
};

function DailySummary() {
  const [data, setData] = useState<Daily | null>(null);

  useEffect(() => {
    const handleRequest = async () => {
      const today = new Date().toISOString().split("T")[0];
      const res = await fetch(
        "http://localhost:8080/study/daySummary/?date=" + today,
        {
          headers: {
            Authorization: "Bearer " + localStorage.getItem("token"),
          },
        }
      );

      if (res.ok) {
        const json = await res.json();
        setData(json);
      }
    };

    handleRequest();
  }, []);

  if (!data) return <div className="loading">Loading...</div>;

  return (
    <div className="dailyContainer">
      <h1>Today Summary</h1>
      <p className="date">{data.date}</p>

      <p className={`status ${data.isStudied ? "studied" : "notStudied"}`}>
        {data.isStudied ? "Studied ✅" : "Not Studied ❌"}
      </p>

      <div className="hoursCard">
        <h3>Total Hours</h3>
        <p>{data.totalHours} hrs</p>
      </div>

      <h3 className="sectionTitle">Category Breakdown</h3>

      {Object.keys(data.categories).length === 0 ? (
        <p className="empty">No categories logged today.</p>
      ) : (
        <ul className="categoryList">
          {Object.entries(data.categories).map(([cat, hrs]) => (
            <li key={cat}>
              <span>{cat}</span>
              <span>{hrs} hrs</span>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default DailySummary;