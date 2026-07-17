import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import "./index.css";
import App from "./App.jsx";
import PurseList from "./components/purses/purse-list/purselist";
import Nav from 'react-bootstrap/Nav';
import { createBrowserRouter, RouterProvider, Link } from "react-router-dom";
import NavBar from "./components/navbar/navbar.jsx";
import EventsList from "./components/events/events-list/eventslist.jsx";
import CurrenciesList from "./components/currencies/currencies-list/currenciestlist.jsx";

const router = createBrowserRouter([
  { // route 1
    element: <NavBar></NavBar>,
    children: [
      { path: "/", element: <App /> },
      { path: "/purselist", element: <PurseList /> },
      { path: "/purse/:id", element: <EventsList />},
      { path: "/currencieslist", element: <CurrenciesList />},
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
