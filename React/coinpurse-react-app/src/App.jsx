import { useState } from 'react'
import './App.css'
import 'bootstrap/dist/css/bootstrap.min.css';
import PurseCard from './components/purses/purse-card/pursecard'
import PurseList from './components/purses/purse-list/purselist'


function App() {

  return (
    <>
      <section id="center">
      <h2>Tests</h2>
      <PurseList></PurseList>
      </section>
    </>
  )
}

export default App
