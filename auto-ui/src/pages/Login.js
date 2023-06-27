import { useState, useEffect } from "react";
import { TextField } from "@mui/material";
import { Navigate, useNavigate } from "react-router-dom";
import { useHistory } from "react-router-dom";

function LoginPage() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [logedIn, setLogedIn] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    if (logedIn) {
      navigate("/home");
    }
  }, [logedIn, navigate]);

  const handleSubmit = (event) => {
    event.preventDefault();

    fetch("api/v1/login", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        username,
        password,
      }),
    })
      .then((response) => {
        if (response.ok) {
          return response.text();
        } else {
          throw new Error("Invalid credentials");
        }
      })
      .then((token) => {
        localStorage.setItem("jwtToken", token);
        setLogedIn(true);
        // navigate("/home");
        window.location = "/home";
      })
      .catch((error) => {
        // Handle login error
      });
  };

  return (
    <div className="mx-3 row">
      <div className="col-md-8 col-lg-6 mx-auto">
        <h2 className="my-5">Prisijunkite</h2>
        <TextField
          required
          onChange={(e) => setUsername(e.target.value)}
          className="form-control mb-3"
          size="small"
          id="username"
          label="Vartotojo vardas"
          value={username}
        />
        <TextField
          required
          onChange={(e) => setPassword(e.target.value)}
          className="form-control mb-5"
          size="small"
          id="password"
          label="Slaptažodis"
          type="password"
          value={password}
        />
        <button className="btn btn-primary me-2 mb-5" onClick={handleSubmit}>
          Prisijungti
        </button>
        {/* <button className="btn btn-secondary me-2 mb-5" onClick={handleRegister}>
          Registruotis
        </button> */}
      </div>
    </div>
  );
}

export default LoginPage;
