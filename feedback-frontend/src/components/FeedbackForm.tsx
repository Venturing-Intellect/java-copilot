import React, { useState } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';

interface FeedbackFormProps {
  onSubmit: (feedback: { email: string; feedback: string }) => Promise<void>;
}

const FeedbackForm: React.FC<FeedbackFormProps> = ({ onSubmit }) => {
  const [email, setEmail] = useState('');
  const [feedback, setFeedback] = useState('');
  const [message, setMessage] = useState<{ type: 'success' | 'error'; text: string } | null>(null);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    try {
      await onSubmit({ email, feedback });
      setMessage({ type: 'success', text: 'Feedback successfully sent!' });
      setEmail('');
      setFeedback('');
    } catch (error) {
      setMessage({ type: 'error', text: 'There was a problem sending your feedback. Please try again.' });
    }
  };

  return (
    <div className="container mt-5">
      <h2>Submit Feedback</h2>
      {message && (
        <div className={`alert alert-${message.type === 'success' ? 'success' : 'danger'}`} role="alert">
          {message.text}
        </div>
      )}
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