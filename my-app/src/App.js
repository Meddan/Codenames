import React, { Component } from "react";
import "./App.css";
import { postJson, getJson } from "./fetch.js";
import Agent from "./Agent.js";
import styled from "styled-components";
import TextForm from "./TextForm.js";
import { BrowserRouter as Router, Route, Link } from "react-router-dom";
import Board from "./Board.js";
import Main from "./Main.js";

class App extends Component {
  constructor(params) {
    super(params);
    this.state = {};
  }
  render() {
    return (
      <Router>
        <div>
          <Route exact path="/" component={Main} />
          <Route path="/game/:id" component={Board} />
        </div>
      </Router>
    );
  }
  componentWillMount() {
    //this.getboard();
  }
}
const NewGameButton = styled.li`className="NewGamerole="button";to="/";onClick={this.startNewGame}`;
//<button onClick={this.startNewGame}> New Game </button>
export default App;
