import { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import VisibilityTwoToneIcon from "@mui/icons-material/VisibilityTwoTone";
import { apiUrl } from "../App";

function AutoserviceListPage() {
  const [autoservices, setAutoservices] = useState([]);
  const token = localStorage.getItem("jwtToken");


  const fetchAutoservices = () => {
    fetch(`${apiUrl}/autoservice`, {
      headers: {
        Authorization: `Bearer ${token}`,
      },
    })
      .then((response) => response.json())
      .then((jsonResponse) => setAutoservices(jsonResponse));
  };

  useEffect(fetchAutoservices, []);

  return (
    <div className="mx-3">
      <h2 className="my-5">Automobilių servisai</h2>
      <table className="table table-hover shadow p-3 mb-5 bg-body rounded align-middle">
        <thead className="table-light">
          <tr>
            <th>Nr.</th>
            <th>Pavadinimas</th>
            <th>Adresas</th>
            <th>Vadovas</th>
            <th>
              <div></div>
            </th>
          </tr>
        </thead>
        <tbody>
          {autoservices?.map((autoservice) => (
            <tr key={autoservice.id} id={autoservice.id}>
              <td>{autoservice.id}</td>
              <td>{autoservice.name}</td>
              <td>{autoservice.address}</td>
              <td>{autoservice.manager}</td>
              <td className="text-end">
                <button
                  className="btn btn-outline-primary me-1 my-1 btn-link"
                  title="Žiūrėti"
                >
                  <Link className="nav-link" to={"/autoservice/view/" + autoservice.id}>
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

export default AutoserviceListPage;
