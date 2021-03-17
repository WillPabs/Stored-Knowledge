import React, {useState, useEffect} from "react";
import logo from './logo.svg';
import './App.css';
import axios from "axios"

const Books = () => {
  const fetchBooks = () => {
    axios.get("http://localhost:8080/api/v1/books").then(res => {
      console.log(res)
    });
  }

  useEffect(() => {
    fetchBooks();
  }, [])
  return <h1>Hello</h1>
}

function App() {
  return (
    <div className="App">
      <Books/>
    </div>
  );
}

export default App;
