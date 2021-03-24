import React, {useState, useEffect} from "react";
import logo from './logo.svg';
import './App.css';
import axios from "axios"

const Books = () => {

  const [books, setBooks] = useState([]);

  const fetchBooks = () => {
    axios.get("http://localhost:8081/api/v1/books").then(res => {
      console.log(res)
      setBooks(res.data);
    });
  }

  useEffect(() => {
    fetchBooks();
  }, [])
  return books.map((book, index) => {

    return (
      <div key={index}>
        <h3>{book.title}</h3>    
        <p>{book.description}</p>
        <h4>Authors</h4>
        <div>{book.authors.map((author, idx) => { //this allows to display nested data
          return (
            <div>
              
              <p>{author.firstName}</p>
            </div>
          )
        })}</div>
      </div>
    )
  })
}

const Authors = () => {

  const [authors, setAuthors] = useState([]);

  const fetchAuthors = () => {
    axios.get("http://localhost:8081/api/v1/authors").then(res => {
      console.log(res);
      setAuthors(res.data);
    })
  }

  useEffect(() => {
    fetchAuthors();
  }, [])
  return authors.map((author, index) => {

    return (
      <div key={index}>
        <h3>{author.firstName} {author.lastName}</h3>
        {/* Find way to display list of books in author object for code
        below*/}
        {/* <div>{author.books.map((book, idx) => {
          return (
            <div>
              <p>{book.title}</p>
            </div>
          )
        })}
        </div> */}
      </div>
    )
  })
}

const Users = () => {

  const [users, setUsers] = useState([]);

  const fetchUsers = () => {
    axios.get("http://localhost:8081/api/v1/users").then(res => {
      console.log(res);
      setUsers(res.data);
    })
  }

  useEffect(() => {
    fetchUsers();
  }, [])
  return users.map((user, index) => {

    return (
      <div key={index}>
        <h3>{user.firstName} {user.lastName}</h3>
        <p>{user.username}</p>
        <p>{user.email}</p>
      </div>
    )
  })
}

function App() {
  return (
    <div className="App">
      <div>
        <h1>Books</h1>
        <Books/>
      </div>
      <div>
        <h1>Authors</h1>
        <Authors/>
      </div>
      <div>
        <h1>Users</h1>
        <Users/>
      </div>
    </div>
  );
}

export default App;
