import React from 'react';
import ReactDom from 'react-dom';

import Button from 'material-ui/Button';

import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
const App = () => (



    //
    <div>
    <MuiThemeProvider>
        <div>




            <Button>
                Hello World
            </Button>

        </div>
    </MuiThemeProvider>
    </div>

);

ReactDom.render(<App/>,document.getElementById('root'));

function getDog() {

}