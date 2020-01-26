import 'bootstrap/dist/css/bootstrap.css';
import React from 'react';
import {Col, Container, Row} from 'reactstrap';
import Filters from './components/Filters';
import Header from './components/Header';
import MatchList from './components/MatchList';


class App extends React.Component {

	constructor(props) {
		super(props);
		this.state = {host: 'http://localhost:9090', filters: {}};
		this.onFilterChange = this.onFilterChange.bind(this);
	}

	onFilterChange(filters) {
		this.setState({filters: filters});
		console.log(JSON.stringify(this.state.filters))
	}

	render() {
		return (
			<div>
				<Header/>
				<Container fluid>
					<Row>
						<Col md='2'>
							<Filters onFilterChange={this.onFilterChange}/>
						</Col>
						<Col>
							<MatchList host={this.state.host} filters={this.state.filters}/>
						</Col>
					</Row>
				</Container>
			</div>
		)
	}
}

export default App;