import React from 'react';
import App from "./App";

class Form extends React.Component {
    constructor(props) {
        super(props);
        this.state = {value: ''};

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        this.setState({value: event.target.value});
    }

    handleSubmit(event) {
        alert('A name was submitted: ' + this.state.value);
        //TODO POST to backend
        event.preventDefault();
    }

    //TODO make this a scroll menu of pint types in DB instead of type input for validation

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <label>
                    Pint Name:
                    <input type="text" value={this.state.value} onChange={this.handleChange} />
                </label>
                <input type="submit" value="Submit" />
            </form>
        );
    }
}

export default Form;