import React, { Component } from "react";
import "./App.css";
import { postJson, getJson } from "./fetch.js";
import Agent from "./Agent.js";
import styled from "styled-components";
import TextForm from "./TextForm.js";

class Main extends Component {
  render() {
    return (
      <div>
        <h1>CODENAMES</h1>
        <button onClick={this.startNewGame}> New Game </button>
        <TextForm id="Game ID" buttonText="Go" />
      </div>
    );
  }

  startNewGame = () => {
    const promise = postJson("/newgame", null);
    promise
      .then(resp => {
        window.location = document.location.origin + "/game/" + resp.response;
      })
      .catch(err => console.log(err));
  };
}

export default Main;
