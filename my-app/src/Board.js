import React, { Component } from "react";
import logo from "./logo.svg";
import "./App.css";
import { postJson, getJson } from "./fetch.js";
import Agent from "./Agent.js";
import styled from "styled-components";

class Board extends Component {
  constructor({ match }) {
    super(match.props);
    console.log("match ", match);
    console.log("params ", match.params);
    console.log("id ", match.params.id);
    this.state = {
      id: match.params.id,
      board: []
    };
  }
  render() {
    console.log("render");
    console.log("this.state", this.state);
    const { board } = this.state.board;
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
  componentWillMount() {
    const promise = getJson("/game/" + this.state.id, null);

    promise
      .then(resp => {
        //this.setState({ board: resp.agents });
        //console.log(this.board);
        console.log("got resp");
        console.log("resp.gameBoard.agents ", resp.gameBoard.agents);

        this.setState({ board: resp.gameBoard.agents });

        console.log("this.board", this.state.board);
      })
      .catch(err => {
        console.log("promise broken");
        console.log(err);
      });
  }
}
/*

*/

const Row = styled.div`display: flex;`;
export default Board;
