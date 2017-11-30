import React from 'react';
import ReactDom from 'react-dom';

import Button from 'material-ui/Button';
import SimpleAppBar from './components/appBar'
import PaperSheet from './components/papersheet'

const App = () => (
    <div className='container-fluid'>

<SimpleAppBar/>
        <PaperSheet/>




    </div>

);

ReactDom.render(<App/>, document.getElementById('root'));
