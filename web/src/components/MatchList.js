import React from 'react';
import {
	Card,
	CardBody,
	CardColumns,
	CardHeader,
	CardImg,
	CardText,
	Col,
	Container,
	Jumbotron,
	Progress,
	Row,
	Table
} from 'reactstrap';
import client from './client';

class MatchList extends React.Component {

	constructor(props) {
		super(props);
		this.state = {
			matches: []
		};
		this.updateState = this.updateState.bind(this);
	}

	updateState(response) {
		this.setState({
			matches: response.entity
		});
	}

	componentDidMount() {
		this.updateMatchList();
	}

	componentDidUpdate(prevProps, prevState) {
		if (JSON.stringify(prevProps.filters) !== JSON.stringify(this.props.filters)) {
			this.updateMatchList();
		}
	}

	updateMatchList() {
		client({method: 'GET', path: this.getMatchesURI()}).done(this.updateState);
	}

	getMatchesURI() {
		var baseURI = '/matches/filter?';
		let filters = new URLSearchParams(this.props.filters).toString();
		console.log("Requesting: " + baseURI + filters);
		return baseURI + filters;
	}

	getGroupedMatch() {
		var matchesArray = this.state.matches;
		var matchesPerRow = 3;
		return matchesArray.map((item, index) => {
			return index % matchesPerRow === 0 ? matchesArray.slice(index, index + matchesPerRow) : null;
		}).filter(function (item) {
			return item;
		});
	}

	render() {
		if (this.state.matches && this.state.matches.length > 0) {
			const cardDeckGroup = this.getGroupedMatch().map((group, i) => {
				return <CardDeckGroup key={'card-deck-group' + i} group={group}/>
			});
			return (
				<div>
					<br/>
					<Container fluid>
						<Row>
							<Col>
								{cardDeckGroup}
							</Col>
						</Row>
					</Container>
				</div>
			)
		} else {
			return (
				<div>
					<Jumbotron>
						<h1>No match found 💔</h1>
						<hr className="my-2"/>
						<p>Pls try changing filters..</p>
					</Jumbotron>
				</div>
			);
		}
	}
}

class CardDeckGroup extends React.Component {
	render() {
		const items = this.props.group.map(match => <Match key={match.displayName} match={match}/>);
		return (
			<div align='middle'>
				<CardColumns>
					{items}
				</CardColumns>
				<br/>
			</div>
		)
	}
}

class Match extends React.Component {
	render() {
		return (
			<Card>
				<CardHeader tag="h5">
					<Row>
						<Col>
							<div class='float-left'>
								<h5 className="text-danger">
									<strong>
										{this.props.match.displayName}
									</strong>
								</h5>
							</div>
						</Col>
						<Col>
							<div class='float-right'>
								{this.getFavIcon()}
							</div>
						</Col>
					</Row>
				</CardHeader>
				<CardImg top src={this.getProfileImage()} alt="Card image cap"/>
				<CardBody>
					<CardText>
						<Table responsive>
							<tr>
								<th>{this.props.match.age} yrs</th>
								<td scope="row">
									<img src="http://maps.gstatic.com/mapfiles/ridefinder-images/mm_20_green.png"
										 alt=""/>
									&nbsp; {this.props.match.city.name}
								</td>
							</tr>
							<tr>
								<td>{this.props.match.jobTitle}</td>
								<td>{this.props.match.dis} km away</td>
							</tr>
							<tr>
								<td>{this.props.match.religion}</td>
								<td>{this.props.match.height}"</td>
							</tr>
						</Table>
						<Table responsive>
							<tr>
								<Progress striped value={this.getMatchScore()} max={100}>
									{this.getMatchScore()}% match
								</Progress>
							</tr>
						</Table>
					</CardText>
				</CardBody>
			</Card>
		)
	}

	getMatchScore() {
		return this.props.match.compatibilityScore;
	}

	getFavIcon() {
		if (this.props.match.favourite) {
			return <span>💗</span>;
		} else {
			return "";
		}
	}

	getProfileImage() {
		if (this.props.match.mainPhoto == null) {
			return "https://via.placeholder.com/450x450?text=No Image";
		} else {
			return this.props.match.mainPhoto;
		}
	}
}

export default MatchList;