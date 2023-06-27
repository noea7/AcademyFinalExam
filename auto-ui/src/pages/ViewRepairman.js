import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import jwtDecode from "jwt-decode";
import { apiUrl } from "../App";

function ViewRepairmanPage() {
  const [repairman, setRepairman] = useState({});
  const [rating, setRating] = useState("");
  const params = useParams();
  const token = localStorage.getItem("jwtToken");
  const role = jwtDecode(token).role;

  useEffect(() => {
    fetch(`${apiUrl}/repairman/` + params.id, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => response.json())
      .then((jsonResponse) => setRepairman(jsonResponse));
  }, [params.id]);

  useEffect(() => {
    fetch(`${apiUrl}/rating/` + params.id, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => response.text())
      .then((textResponse) => setRating(textResponse));
  }, [params.id]);

  return (
    <div className="mx-3">
      <h2 className="my-5">Informacija apie meistrą</h2>
      <div key={repairman.id} id={repairman.id}>
        <h4>
          {repairman?.firstName} {repairman?.lastName}
        </h4>
        <h5>Įvertinimas: {rating}</h5>
        <h6>{repairman?.specialization}</h6>
        <p>
          Autoservisas: {repairman?.autoservice?.name}{" "}
          {repairman?.autoservice?.address}
        </p>
      </div>
      {role === "admin" ? (
        <div>
          <button className="btn btn-secondary me-2 mb-5">Redaguoti</button>
          <button className="btn btn-danger me-2 mb-5">Ištrinti</button>
        </div>
      ) : (
        ""
      )}
    </div>
  );
}

export default ViewRepairmanPage;
