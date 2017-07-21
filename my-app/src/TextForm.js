import React, { Component } from "react";
class TextForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      value: "",
      id: props.id,
      buttonText: props.buttonText
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  handleChange(event) {
    this.setState({ value: event.target.value });
  }

  handleSubmit(event) {
    alert("A name was submitted: " + this.state.value);
    event.preventDefault();
  }

  render() {
    return (
      <form onSubmit={this.handleSubmit}>
        <label>
          {this.state.id}
          <input
            type="text"
            value={this.state.value}
            onChange={this.handleChange}
          />
        </label>
        <input type="submit" value={this.state.buttonText} />
      </form>
    );
  }
}

export default TextForm;
