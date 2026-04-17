import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.jsx";
import PurseList from "./components/purses/purse-list/purselist";
import Nav from 'react-bootstrap/Nav';
import { createBrowserRouter, RouterProvider, Link } from "react-router-dom";

const router = createBrowserRouter([
  { path: "/", element: <App /> },
  { path: "/purselist", element: <PurseList /> },
  { path: "*", element: <App /> },
]);

createRoot(document.getElementById("root")).render(
  <StrictMode>
      <Nav fill variant="tabs" defaultActiveKey="/home">
      <Nav.Item>
        <Nav.Link eventKey="link-1" href="/home">Home</Nav.Link>
      </Nav.Item>
      <Nav.Item>
        <Nav.Link eventKey="link-2" href="/purselist">Purses</Nav.Link>
      </Nav.Item>
    </Nav>
      <RouterProvider router = {router}>
      </RouterProvider>
  </StrictMode>,
);
