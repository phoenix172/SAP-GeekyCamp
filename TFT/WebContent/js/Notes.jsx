const basePath = "/TFT/notes/";
class Notes extends React.Component {
    constructor() {
        super();
    }
    
    state = {
            noteContent: "",
            notes: []
        };

    renderCreateField() {
        return (
            <form className='form-group'>
                <textarea className='form-control' 
                    id='contentArea' onChange={( e ) => this.textChanged( e.target.value )} value={this.state.noteContent}/>
                <a className='form-control btn btn-primary' href='#' role='button' 
                    onClick={() => this.addNote()}>Save</a>
            </form>
            );
    }

    textChanged( newContent ) {
        let s = this.state;
        s.noteContent = newContent;
        this.setState( s );
    }

    addNote() {
        if(!this.state.noteContent)return;
        $.ajax( {
            url: basePath,
            method: "POST",
            data: JSON.stringify({content: this.state.noteContent}),
            contentType: "application/json",
            success: (newNote) => {
                let s = this.state;
                s.notes.push(JSON.parse(newNote));
                s.noteContent = "";
                this.setState(s)
            },
            error: xhr =>
                console.log(xhr)
        } );
    }
    
    deleteNote(id){
        $.ajax({
            method: "DELETE",
            url: basePath+id,
            success: ()=>{
                let updatedNotes 
                    = this.state.notes.filter(note=> note.id!=id);                
                this.setState({notes: updatedNotes});
            },
            error: xhr=>
                alert("Could not delete record Status: "
                        +xhr.status + ", Error: "+xhr.responseText)
        });
    }
    
    componentDidMount(){
        $.ajax({
           url: basePath,
           success: notes=>this.setState({notes: notes}),
           error: xhr=>alert("Status: "+xhr.status + "\n\n"+xhr.responseText)
        });
    }

    renderNote(note){
        return (<div className='row alert alert-success' key={note.id}>
                    <div className='col-2 mx-auto text-center' >
                        <a href='#' role='button' className='btn btn-danger'
                            onClick={()=>this.deleteNote(note.id)}>Delete</a>
                    </div>
                    <div className='col text-center'>{note.content}</div>
                </div>)
    }
    
    renderNotes() {
        return (
            <div>
                <div className='row' >
                        <div className='alert alert-dark col-2 text-center'>Delete</div>
                        <div className='alert alert-dark col text-center'>Content</div>
                </div>
                <div className='clear-fix'></div>
                {this.state.notes.map(x=>this.renderNote(x))}
            </div>
            );
    }

    render() {
        return (
            <div className='container rounded  panel panel-default'>
                {this.renderNotes()}
                {this.renderCreateField()}
            </div>
        );
    }
}

ReactDOM.render(<Notes />,
        document.getElementById('app'));