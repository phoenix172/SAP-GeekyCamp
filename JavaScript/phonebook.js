var greeting = "Hello";

class Welcome extends React.Component{
    render(){
        return <h1>{this.props.name}</h1>; 
    }
}

ReactDOM.render(
    <Welcome name="Pesho"/>,
    document.getElementById('app')
);