import {
	Button,
	Collapse,
	Form,
	Input,
	InputGroup,
	InputGroupAddon,
	InputGroupText,
	Nav,
	Navbar,
	NavbarToggler,
	NavItem
} from 'reactstrap';

const React = require('react');

class Filters extends React.Component {

	constructor(props) {
		super(props);
		this.state = {isOpen: true, filters: {}};
		this.toggle = this.toggle.bind(this);
		this.onInputUpdate = this.onInputUpdate.bind(this);
		this.onCheckboxUpdate = this.onCheckboxUpdate.bind(this);
		this.setFilter = this.setFilter.bind(this);
		this.onFilterApply = this.onFilterApply.bind(this);
	}

	toggle() {
		this.setState({
			isOpen: !this.state.isOpen
		});
	}

	onInputUpdate(e) {
		e.preventDefault();
		this.setFilter(e.target.name, e.target.value);
	}

	onCheckboxUpdate(e) {
		let isChecked = e.target.checked;
		if (isChecked) {
			this.setFilter(e.target.name, 1);
		} else {
			this.setFilter(e.target.name, "");
		}
	}

	setFilter(name, value) {
		const newFilters = Object.assign({}, this.state.filters);
		Object.assign(newFilters, {[name]: value});
		this.setState({filters: newFilters});
	}

	onFilterApply(event) {
		console.log(JSON.stringify(this.state.filters));
		this.props.onFilterChange(this.state.filters);
	}

	render() {
		return (
			<div>
				<hr/>
				<Navbar light>
					<h5>Filters</h5>
					<NavbarToggler onClick={this.toggle}/>
					<Collapse isOpen={this.state.isOpen} navbar>
						<hr/>
						<Nav>
							<NavItem>
								<Form>
									<InputGroup>
										<InputGroupAddon addonType="prepend">
											<InputGroupText>
												<Input addon name="isFavourite" type="checkbox"
													   value={this.state.filters.isFavourite}
													   onChange={this.onCheckboxUpdate}/>
												&nbsp;&nbsp;&nbsp;&nbsp;
												💗 Favourite
											</InputGroupText>
										</InputGroupAddon>
									</InputGroup>
									<hr/>
									<InputGroup>
										<InputGroupAddon addonType="prepend">
											<InputGroupText>
												<Input addon name="hasPhoto" type="checkbox"
													   value={this.state.filters.hasPhoto}
													   onChange={this.onCheckboxUpdate}/>
												&nbsp;&nbsp;&nbsp;&nbsp;
												🖼️ Has photo
											</InputGroupText>
										</InputGroupAddon>
									</InputGroup>
									<hr/>
									<InputGroup>
										<InputGroupAddon addonType="prepend">
											<InputGroupText>
												<Input addon name="hasContact" type="checkbox"
													   value={this.state.filters.hasContact}
													   onChange={this.onCheckboxUpdate}/>
												&nbsp;&nbsp;&nbsp;&nbsp;In Contact
											</InputGroupText>
										</InputGroupAddon>
									</InputGroup>
									<hr/>
									<InputGroup>
										<InputGroupAddon addonType="prepend">
											<InputGroupText>Age</InputGroupText>
										</InputGroupAddon>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<Input name='ageTo' type="range" min="18" max="95" onChange={this.onInputUpdate}
											   value={this.state.filters.ageTo} placeholder="from">
										</Input>
										<h6>&nbsp;{this.state.filters.ageTo}</h6>
									</InputGroup>
									<hr/>
									<InputGroup>
										<InputGroupAddon addonType="prepend">
											<InputGroupText>Height</InputGroupText>
										</InputGroupAddon>
										&nbsp;&nbsp;&nbsp;&nbsp;

										<Input name='heightTo' type="range" min="135" max="210"
											   onChange={this.onInputUpdate}
											   value={this.state.filters.heightTo} placeholder="from">
										</Input>
										<h6>&nbsp;{this.state.filters.heightTo}</h6>
									</InputGroup>
									<hr/>
									<InputGroup>
										<InputGroupAddon addonType="prepend">
											<InputGroupText>Match %</InputGroupText>
										</InputGroupAddon>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<Input name='csTo' type="range" min="1" max="99" onChange={this.onInputUpdate}
											   value={this.state.filters.csTo} placeholder="from">
										</Input>
										<h6>&nbsp;{this.state.filters.csTo}</h6>
									</InputGroup>
									<hr/>
									<InputGroup>
										<InputGroupAddon addonType="prepend">
											<InputGroupText>Distance</InputGroupText>
										</InputGroupAddon>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<br/>
										<Input name='distance' type="range" min="1" max="300"
											   onChange={this.onInputUpdate}
											   value={this.state.filters.distance} placeholder="from">
										</Input>
										<h6>&nbsp;{this.state.filters.distance}</h6>
									</InputGroup>
									<hr/>
									<Button outline color="secondary"
											onClick={this.onFilterApply}>Apply</Button>
								</Form>
							</NavItem>
						</Nav>
					</Collapse>
				</Navbar>
				<hr/>
			</div>
		)
	}
}

export default Filters;