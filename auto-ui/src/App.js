import { BrowserRouter, Route, Routes } from "react-router-dom";
import jwtDecode from "jwt-decode";
import Navigation from "./components/Navigation";
import AdminHomePage from "./pages/AdminHome";
import UserHomePage from "./pages/UserHome";
import LoginPage from "./pages/Login";
import AutoserviceListPage from "./pages/AutoserviceList";
import ViewAutoservicePage from "./pages/ViewAutoservice";
import RepairerListPage from "./pages/RepairmanList";
import ViewRepairmanPage from "./pages/ViewRepairman"; 
import "./App.css";

const apiUrl = "http://localhost:3000/api/v1";

function App() {
  const token = localStorage.getItem("jwtToken");
  let role = "";

  if (token) {
    const decodedToken = jwtDecode(token);
    role = decodedToken.role;
  }
  return (
    <BrowserRouter>
      <Navigation />
      <div className="container-xxl">
        <Routes>
          {token && (
            <Route
              path="/home"
              element={role === "admin" ? <AdminHomePage /> : <UserHomePage />}
            />
          )}
          {token && (
            <Route path="/autoservice" element={<AutoserviceListPage />} />
          )}
          {token && <Route path="/repairman" element={<RepairerListPage />} />}
          {token && (
            <Route
              path="/autoservice/view/:id"
              element={<ViewAutoservicePage />}
            />
          )}
          {token && (
            <Route
              path="/repairman/view/:id"
              element={<ViewRepairmanPage />}
            />
          )}
          {!token && <Route path="/*" element={<LoginPage />} />}
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;
export { apiUrl };