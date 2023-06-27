import { useState, useEffect } from "react";
import { useParams } from "react-router-dom";
import jwtDecode from "jwt-decode";
import { apiUrl } from "../App";

function ViewAutoservicePage() {
  const [autoservice, setAutoservice] = useState({});
  const params = useParams();
  const token = localStorage.getItem("jwtToken");
  const role = jwtDecode(token).role;

  useEffect(() => {
    fetch(`${apiUrl}/autoservice/` + params.id, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => response.json())
      .then((jsonResponse) => setAutoservice(jsonResponse));
  }, [params.id]);

  return (
    <div className="mx-3">
      <h2 className="my-5">Informacija apie servisą</h2>
      {/* <h4>Nr. {autoservice?.id}</h4> */}
      <h4> {autoservice?.name}</h4>
      <h6>Adresas {autoservice?.address}</h6>
      <p>Vadovas {autoservice?.manager}</p>
      {role === "admin" ?
      <div>
      <button className="btn btn-secondary me-2 mb-5">Redaguoti</button>
      <button className="btn btn-danger me-2 mb-5">Ištrinti</button>
      </div> : ""
      }
    </div>
  );
}

export default ViewAutoservicePage;
