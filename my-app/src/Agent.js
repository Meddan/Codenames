import React, { Component } from "react";
import "./App.css";
import { postJson, getJson } from "./fetch.js";
import styled from "styled-components";

class Agent extends Component {
  render() {
    return (
      <Container
        style={{ border: `5px solid ${this.teamToColor()}` }}
        className="Agent"
      >
        <H1>
          {this.state.word}
        </H1>
      </Container>
    );
  }
  componentWillMount() {}
  teamToColor = () => {
    const { team } = this.state;
    if (team == "Red") {
      return "red";
    } else if (team == "Blue") {
      return "blue";
    } else if (team == "Civilian") {
      return "beige";
    } else {
      return "black";
    }
  };
  constructor(props) {
    super(props);
    this.state = {
      word: props.agent.word,
      team: props.agent.team,
      revealed: props.agent.revealed
    };
  }
}

const H1 = styled.h3`text-align: center;`;
const Container = styled.div`flex: 1;`;
export default Agent;
