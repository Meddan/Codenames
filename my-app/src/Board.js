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
      board: []
    };
    const promise = getJson("/game/" + this.state.id, null);

    promise
      .then(resp => {
        //this.setState({ board: resp.agents });
        //console.log(this.board);

        this.setState({ board: resp.gameBoard.agents });
      })
      .catch(err => {
        console.log(err);
      });
  }
  render() {
    const board = this.state.board;
    console.log(board);
    return (
      <div className="App-intro">
        {board.map(sublist => {
          return (
            <Row>
              {sublist.map(agent => <Agent agent={agent} />)}
            </Row>
          );
        })}
      </div>
    );
  }
}
/*

*/

const Row = styled.div`display: flex;`;
export default Board;
