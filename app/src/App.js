import React, {Component} from 'react'
import axios from 'axios'
import ReactPaginate from 'react-paginate';
import "./style.css";

export default class App extends Component {
    constructor(props) {
        super(props);
        this.state = {
            totalPages: 0,
            pageSize: 5,
            currentPage: 0,
            totalElements: 0,
            data: [],
            searchText: ''
        };
        this.handlePageClick = this
            .handlePageClick
            .bind(this);
    }

    receivedData() {
        axios
            .get(`http://localhost:8080/contacts?pageNumber=${this.state.currentPage}&pageSize=${this.state.pageSize}`)
            .then(res => {

                const data = res.data;
                let postData = data.content.map(pd =>
                    <tr>
                        <td>{pd.name}</td>
                        <td><img src={pd.url} alt=""/></td>
                    </tr>)

                this.setState({
                    totalPages: data.totalPages,
                    totalElements: data.totalElements,
                    data: postData
                })
            });
    }

    handlePageClick = (e) => {
        const selectedPage = e.selected;

        this.setState({
            currentPage: selectedPage,
        }, () => {
            this.receivedData()
        });

    };

    componentDidMount() {
        this.receivedData()
    }

    changePageSize(size) {
        this.setState({
            pageSize: size,
            searchText: ""
        }, () => {
            this.receivedData()
        });
    }

    handleTextChange = (event) => {
        this.setState({
            searchText: event.target.value
        })
    }

    handleSubmit = (event) => {

        axios
            .get(`http://localhost:8080/contacts/${this.state.searchText}`)
            .then(res => {

                const data = res.data;
                let postData = data.map(pd =>
                    <tr>
                        <td>{pd.name}</td>
                        <td><img src={pd.url} alt=""/></td>
                    </tr>)

                this.setState({
                    totalPages: data.totalPages,
                    totalElements: data.totalElements,
                    data: postData
                })
            });

    }

    handleClear = (event) => {
        this.setState({
            searchText: ""
        })
        this.receivedData()
    }

    render() {
        return (
            <div>
                <label>
                    Name:
                    <input type="text" value={this.state.searchText} onChange={this.handleTextChange}/>
                </label>
                <button onClick={this.handleSubmit}>Search</button>
                <button onClick={this.handleClear}>Clear!</button>
                {this.state.data}
                <ReactPaginate
                    previousLabel={"prev"}
                    nextLabel={"next"}
                    breakLabel={"..."}
                    breakClassName={"break-me"}
                    pageCount={this.state.totalPages}
                    marginPagesDisplayed={2}
                    pageRangeDisplayed={this.state.pageSize}
                    onPageChange={this.handlePageClick}
                    containerClassName={"pagination"}
                    subContainerClassName={"pages pagination"}
                    activeClassName={"active"}/>
                <select value={this.state.pageSize} onChange={(e) => this.changePageSize(e.target.value)}>
                    {
                        [5, 10, 20, 50].map((size) => (
                            <option key={size} value={size}>
                                Show {size}
                            </option>
                        ))
                    }
                </select>
            </div>

        )
    }
}
