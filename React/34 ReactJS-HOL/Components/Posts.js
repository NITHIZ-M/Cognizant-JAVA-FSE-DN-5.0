// src/Posts.js
import React, { Component } from 'react';
import Post from './Post';

class Posts extends Component {
  render() {
    const blogPosts = [
      {
        id: 1,
        title: 'Understanding React Components',
        author: 'Alice',
        content: 'Components let you split the UI into independent, reusable pieces.'
      },
      {
        id: 2,
        title: 'Why use JSX?',
        author: 'Bob',
        content: 'JSX allows us to write HTML in React easily.'
      },
      {
        id: 3,
        title: 'Props vs State',
        author: 'Charlie',
        content: 'Props are read-only, while state can be changed inside a component.'
      }
    ];

    return (
      <div>
        <h1>Blog Posts</h1>
        {blogPosts.map(post => (
          <Post
            key={post.id}
            title={post.title}
            author={post.author}
            content={post.content}
          />
        ))}
      </div>
    );
  }
}

export default Posts;
