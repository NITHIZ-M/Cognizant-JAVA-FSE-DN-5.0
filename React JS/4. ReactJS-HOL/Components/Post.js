// src/Post.js
import React from 'react';

// Functional component for a single post
const Post = ({ title, author, content }) => {
  return (
    <div style={{ border: '1px solid #ddd', padding: '15px', margin: '15px', borderRadius: '8px' }}>
      <h2>{title}</h2>
      <h4>By: {author}</h4>
      <p>{content}</p>
    </div>
  );
};

export default Post;
