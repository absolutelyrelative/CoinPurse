import { useState } from "react";
import "./App.css";
import "bootstrap/dist/css/bootstrap.min.css";
import PurseCard from "./components/purses/purse-card/pursecard";
import PurseList from "./components/purses/purse-list/purselist";
import Tab from "react-bootstrap/Tab";
import Tabs from "react-bootstrap/Tabs";
import { LineChart } from '@mui/x-charts/LineChart';

function App() {
  return (
    <>
      <section id="center">
        <h2>Tests</h2>
        
      </section>
      <LineChart
        xAxis={[{ data: [1, 2, 3, 5, 8, 10] }]}
        series={[
          {
            data: [2, 5.5, 2, 8.5, 1.5, 5],
          },
        ]}
        height={300}
      />
    </>
  );
}

export default App;
