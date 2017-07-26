import React, { Component } from "react";
import logo from "./logo.svg";
import "./App.css";
import { postJson, getJson } from "./fetch.js";
import Agent from "./Agent.js";
import styled from "styled-components";

class Board extends Component {
  constructor({ match }) {
    super(match.props);
    this.state = {
      id: match.params.id,
      board: [],
      currentTurn: "",
      exists: false
    };
    const gameExists = getJson("/game/" + this.state.id + "/active", null);
    gameExists
      .then(activeResp => {
        console.log(activeResp);
        this.setState({ exists: activeResp });
        const promise = getJson("/game/" + this.state.id, null);

        promise
          .then(resp => {
            this.setState({
              board: resp.gameBoard.agents,
              currentTurn: resp.currentTurn
            });
          })
          .catch(err => {
            console.log(err);
          });
      })
      .catch(err => {
        console.log(err);
      });
  }
  render() {
    const board = this.state.board;
    console.log(board);
    console.log("exists? ", this.state.exists);
    return (
      <div className="App-intro">
        <div>
          <h2>
            {this.state.exists.toString()}
          </h2>
        </div>
        <div>
          {board.map(sublist => {
            return (
              <Row>
                {sublist.map(agent => <Agent agent={agent} />)}
              </Row>
            );
          })}
        </div>
      </div>
    );
  }
}
/*
const teamAndRole = {
  hasTeamAndRole: false,
  team: none,
  role: none,

  authenticate(cb) {
    this.isAuthenticated = true;
    setTimeout(cb, 100); // fake async
  },
  signout(cb) {
    this.isAuthenticated = false;
    setTimeout(cb, 100);
  }
};*/

const Row = styled.div`display: flex;`;
export default Board;
