import { useState, useEffect } from "react";
import { Alert, Collapse, TextField } from "@mui/material";
import { Navigate, useNavigate } from "react-router-dom";
import { apiUrl } from "../App";

function LoginPage() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [logedIn, setLogedIn] = useState(false);
  const [success, setSuccess] = useState(false);
  const [failure, setFailure] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    if (logedIn) {
      navigate("/home");
    }
  }, [logedIn, navigate]);

  const handleLogin = (event) => {
    event.preventDefault();

    fetch(`${apiUrl}/login`, {
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

  const handleRegister = (event) => {
    event.preventDefault();

    fetch(`${apiUrl}/register`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        username,
        password,
      }),
    }).then((result) => {
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
    <div className="mx-3 row">
      <div className="col-md-8 col-lg-6 mx-auto">
        <h2 className="my-5">Prisijunkite</h2>
        <Collapse in={success}>
          <Alert
            onClose={() => {
              setSuccess(false);
            }}
            severity="success"
            className="mb-3"
          >
            Sėkmingai užsiregistravote
          </Alert>
        </Collapse>

        <Collapse in={failure}>
          <Alert
            onClose={() => {
              setFailure(false);
            }}
            severity="error"
            className="mb-3"
          >
            Nepavyko užsiregistruoti
          </Alert>
        </Collapse>
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
        <button className="btn btn-primary me-2 mb-5" onClick={handleLogin}>
          Prisijungti
        </button>
        <button
          className="btn btn-secondary me-2 mb-5"
          onClick={handleRegister}
        >
          Registruotis
        </button>
      </div>
    </div>
  );
}

export default LoginPage;
