import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.jsx";
import PurseList from "./components/purses/purse-list/purselist";
import Nav from 'react-bootstrap/Nav';
import { createBrowserRouter, RouterProvider, Link } from "react-router-dom";
import NavBar from "./components/navbar/navbar.jsx";

const router = createBrowserRouter([
  //{ path: "/", element: <App /> },
  //{ path: "/purselist", element: <PurseList /> },
  //{ path: "*", element: <App /> },
  {
    element: <NavBar></NavBar>,
    children: [
      { path: "/", element: <App /> },
      { path: "/purselist", element: <PurseList /> },
      { path: "*", element: <App /> },
    ]
  }
]);

createRoot(document.getElementById("root")).render(
  <StrictMode>
      <RouterProvider router = {router}>
      </RouterProvider>
  </StrictMode>,
);
