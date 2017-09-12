const basePath = "phonebook/records/";

class TableView extends React.Component{
    state = {records: []}
    constuctor(props){
        this.shared = props.shared;
        this.goToForm = this.gotToForm.bind(this);
        this.delete = this.delete.bind(this);
    }

    goToForm(){
        window.location.href=basePath;
    }

    delete(id){
        jQuery.ajax({
            method: "DELETE",
            url: basePath+id,
            success: ()=>{
                let updatedRecords 
                    = this.state.records.filter(record=> record.id!=id);                
                this.setState({records: updatedRecords});
            },
            error: xhr=>
                alert("Could not delete record #"+id +". Status: "
                        +xhr.status + ", Error: "+xhr.responseText)
        });
    }
    
    componentDidMount(){
        jQuery.ajax({
           url: basePath,
           success: data=>this.setState({records: data}),
           error: xhr=>alert("Status: "+xhr.status + "\n\n"+xhr.responseText)
        });
    }
    
    renderActions(id){
        return (
                <td>
                    <a>Edit</a>
                </td>
                <td>
                    <a onClick={()=>this.delete(id)}>Delete</a>
                </td>
        );
    }
    
    renderRows(){
        return this.state.records.map(record=>(
            <tr key={record.id}>
                <td>{record.id}</td>
                <td>{record.firstName}</td>
                <td>{record.lastName}</td>
                <td>{record.number}</td>
                {this.renderActions(record.id)}
            </tr>
            )
        );
    }
    
    render(){
        return (
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>First Name</th>
                            <th>Last Name</th>
                            <th>Number</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.renderRows()}
                    </tbody>
                </table>
        );
    }
}

ReactDOM.render(<EditForm />,
        document.getElementById('app'));