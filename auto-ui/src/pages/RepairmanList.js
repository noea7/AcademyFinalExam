import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import VisibilityTwoToneIcon from "@mui/icons-material/VisibilityTwoTone";
import { apiUrl } from "../App";

function RepairerListPage() {
  const [repairmen, setRepairmen] = useState([]);
  const token = localStorage.getItem("jwtToken");

  const fetchRepairmen = () => {
    fetch(`${apiUrl}/repairman`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => response.json())
      .then((jsonResponse) => setRepairmen(jsonResponse));
  };

  useEffect(fetchRepairmen, []);

  return (
    <div className="mx-3">
      <h2 className="my-5">Meistrai</h2>
      <table className="table table-hover shadow p-3 mb-5 bg-body rounded align-middle">
        <thead className="table-light">
          <tr>
            <th>Nr.</th>
            <th>Vardas</th>
            <th>Pavardė</th>
            <th>Specializacija</th>
            <th>Miestas</th>
            <th>Autoservisas</th>
            <th>
              <div></div>
            </th>
          </tr>
        </thead>
        <tbody>
          {repairmen?.map((repairman) => (
            <tr key={repairman.id} id={repairman.id}>
              <td>{repairman.id}</td>
              <td>{repairman.firstName}</td>
              <td>{repairman.lastName}</td>
              <td>{repairman.specialization}</td>
              <td>{repairman.city}</td>
              <td>{repairman.autoservice?.id}</td>
              <td className="text-end">
                <button
                  className="btn btn-outline-primary me-1 my-1 btn-link"
                  title="Žiūrėti"
                >
                  <Link
                    className="nav-link"
                    to={"/repairman/view/" + repairman.id}
                  >
                    <VisibilityTwoToneIcon />
                  </Link>
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default RepairerListPage;
