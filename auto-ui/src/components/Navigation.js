import { Link } from "react-router-dom";
import jwtDecode from "jwt-decode";

function Navigation() {
  const token = localStorage.getItem("jwtToken");
  let role = "";

  if (token) {
    const decodedToken = jwtDecode(token);
    role = decodedToken.role;
  }

  return (
    <header className="bg-light">
      <div className="container-xxl">
        <nav className="navbar navbar-expand-lg bg-light">
          <div className="container-fluid">
            <button
              className="navbar-toggler"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarSupportedContent"
              aria-controls="navbarSupportedContent"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span className="navbar-toggler-icon"></span>
            </button>
            <div
              className="collapse navbar-collapse"
              id="navbarSupportedContent"
            >
              <div className="navbar-nav me-auto mb-2 mb-lg-0">
                <div className="nav-item">
                  <Link to="/home" className="nav-link">
                    Home
                  </Link>
                </div>
                <div className="nav-item">
                  <Link to="/autoservice" className="nav-link">
                    Servisai
                  </Link>
                </div>
                <div className="nav-item">
                  <Link to="/repairman" className="nav-link">
                    Meistrai
                  </Link>
                </div>
              </div>
            </div>
          </div>
        </nav>
      </div>
    </header>
  );
}

export default Navigation;
