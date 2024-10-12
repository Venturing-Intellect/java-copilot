import React from 'react';
import FeedbackForm from './components/FeedbackForm';

const App: React.FC = () => {
  const handleFeedbackSubmit = async (feedback: { name: string; email: string; feedback: string }) => {
    console.log('Feedback submitted:', feedback);

    try {
      const response = await fetch('http://localhost:8080/api/feedback', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(feedback),
      });

      const data = await response.json();

      if (!response.ok) {
        throw new Error(data.message || 'Network response was not ok');
      }

      console.log('Feedback successfully sent:', data);
      return data;
    } catch (error) {
      console.error('There was a problem with the fetch operation:', error);
      throw error;
    }
  };

  return (
    <div className="App">
      <FeedbackForm onSubmit={handleFeedbackSubmit} />
    </div>
  );
};

export default App;