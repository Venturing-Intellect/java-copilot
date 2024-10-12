#!/bin/bash

# Create project directory
mkdir -p feedback-frontend
cd feedback-frontend

# Initialize a new React project with TypeScript
npx create-react-app . --template typescript

# Install Bootstrap
npm install bootstrap

# Create directories for components and styles
mkdir -p src/components src/styles

# Create FeedbackForm component
cat <<EOL > src/components/FeedbackForm.tsx
import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

interface FeedbackFormProps {
  onSubmit: (feedback: { email: string; feedback: string }) => void;
}

const FeedbackForm: React.FC<FeedbackFormProps> = ({ onSubmit }) => {
  const [email, setEmail] = useState('');
  const [feedback, setFeedback] = useState('');

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    onSubmit({ email, feedback });
    setEmail('');
    setFeedback('');
  };

  return (
    <div className="container mt-5">
      <h2>Submit Feedback</h2>
      <form onSubmit={handleSubmit}>
        <div className="mb-3">
          <label htmlFor="email" className="form-label">Email address</label>
          <input
            type="email"
            className="form-control"
            id="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div className="mb-3">
          <label htmlFor="feedback" className="form-label">Feedback</label>
          <textarea
            className="form-control"
            id="feedback"
            rows={3}
            value={feedback}
            onChange={(e) => setFeedback(e.target.value)}
            required
          ></textarea>
        </div>
        <button type="submit" className="btn btn-primary">Submit</button>
      </form>
    </div>
  );
};

export default FeedbackForm;
EOL

# Create App component
cat <<EOL > src/App.tsx
import React from 'react';
import FeedbackForm from './components/FeedbackForm';

const App: React.FC = () => {
  const handleFeedbackSubmit = (feedback: { email: string; feedback: string }) => {
    console.log('Feedback submitted:', feedback);
    // Here you can add the logic to send the feedback to the backend
  };

  return (
    <div className="App">
      <FeedbackForm onSubmit={handleFeedbackSubmit} />
    </div>
  );
};

export default App;
EOL

# Update index.tsx to include Bootstrap CSS
cat <<EOL > src/index.tsx
import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import 'bootstrap/dist/css/bootstrap.min.css';

ReactDOM.render(
  <React.StrictMode>
    <App />
  </React.StrictMode>,
  document.getElementById('root')
);
EOL

# Create a basic CSS file
cat <<EOL > src/index.css
body {
  font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, 'Noto Sans', sans-serif, 'Apple Color Emoji', 'Segoe UI Emoji', 'Segoe UI Symbol', 'Noto Color Emoji';
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}
EOL

# Print completion message
echo "React frontend setup complete. Run 'npm start' to start the development server."