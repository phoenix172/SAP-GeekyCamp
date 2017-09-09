class TableView extends React.Component{
    state = {records: []}
    constuctor(props){
        this.shared = props.shared
        this.goToForm = this.gotToForm.bind(this);
        this.delete = this.delete.bind(this);
    }

    goToForm(){
        window.location.href="/phonebook/records"
    }

    delete(id){
        let self = this;
        jQuery.ajax({
            method: "DELETE",
            url: "/phonebook/phonebook/records/"+id,
            success: function(){
                let updatedRecords = self.state.records.filter(record=> 
                    self.setState({records: updatedRecords}))
            },
            error: function(xhr){
                alert("Could not delete record #"+id +". Status: "+xhr.status + ", Error: "+xhr.responseText);
            }
        })
    }

}