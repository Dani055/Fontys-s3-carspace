import React from "react";
import "./NavBar.css";
import { NavLink, Link, useNavigate } from "react-router-dom";

function NavBar() {
  const navigate = useNavigate();
  const links = [
    {
      id: 1,
      path: "/",
      text: "Todo List",
    },
    {
      id: 2,
      path: "/user",
      text: "User",
    },
  ];

  return (
    <nav className="navbar navbar-expand-lg navbar-dark">
      <div className="container">
        <NavLink className="navbar-brand header-media-logo" to="/">
          <div className="media">    
            <img src="/logo.png"></img>
          </div>
        </NavLink>
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
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item dropdown">
              <NavLink
                className="nav-link dropdown-toggle"
                to="#"
                id="navbarDropdown"
                role="button"
                data-bs-toggle="dropdown"
                aria-expanded="false"
              >
                Auctions
              </NavLink>
              <ul className="dropdown-menu" aria-labelledby="navbarDropdown">
                <li>
                  <Link className="dropdown-item" to="#">
                    Live auctions
                  </Link>
                </li>
                <li>
                  <hr className="dropdown-divider" />
                </li>
                <li>
                  <Link className="dropdown-item" to="#">
                    Ended actions
                  </Link>
                </li>
              </ul>
            </li>
            <li className="nav-item">
              <NavLink className="nav-link" to="/auction/create">
                Sell a car
              </NavLink>
            </li>
          </ul>
          <ul className="navbar-nav">
            <div className="dropdown text-end">
              <Link
                className="d-block link-dark text-decocation-none dropdown-toggle"
                data-bs-toggle="dropdown"
                area-aria-expanded="false"
              >
                <img
                  src="/default-user-image.png"
                  alt="profile pic"
                  width="32"
                  height="32"
                  className="rounded-circle"
                ></img>
              </Link>
              <ul className="dropdown-menu text-small shadow">
                <li>
                  <span className="mx-3 my-1 fw-bold">Welcome, --user--</span>
                </li>
                <li>
                  <Link className="dropdown-item" to="/profile">
                    My profile
                  </Link>
                </li>
                <li>
                  <hr className="dropdown-divider"/>
                </li>
                <li>
                  <Link className="dropdown-item" to="#">
                    Log out
                  </Link>
                </li>
              </ul>
            </div>
          </ul>
          <button
            type="button"
            onClick={() => navigate("/login")}
            className="btn btn-dark mx-2"
          >
            Log in
          </button>
          <button
            type="button"
            onClick={() => navigate("/register")}
            className="btn btn-outline-dark"
          >
            Register
          </button>
        </div>
      </div>
    </nav>
  );
}

export default NavBar;