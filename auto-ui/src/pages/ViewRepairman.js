import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import { TextField } from "@mui/material";
import jwtDecode from "jwt-decode";
import { apiUrl } from "../App";

function ViewRepairmanPage() {
  const [repairman, setRepairman] = useState({});
  const [rating, setRating] = useState("");
  const [value, setValue] = useState("");
  const [success, setSuccess] = useState(false);
  const [failure, setFailure] = useState(false);
  const params = useParams();
  const token = localStorage.getItem("jwtToken");
  const role = jwtDecode(token).role;
  const userId = jwtDecode(token).id;

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

  const postRating = () => {
    fetch(
      `${apiUrl}/rating/${params.id}/${userId}/${value}`,
      {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
        },
      }
    ).then((result) => {
      if (result.ok) {
        setSuccess(true);
        setFailure(false);
      } else {
        setFailure(true);
        setSuccess(false);
      }
    });
  };

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
      <div>
        <TextField
          onChange={(e) => setValue(e.target.value)}
          value={value}
          id="rating-value"
          label="Rating"
          className="form-control mb-3"
          size="small"
          required
        />
        <button className="btn btn-secondary me-2 mb-5" onClick={postRating}>Įvertinti</button>
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
