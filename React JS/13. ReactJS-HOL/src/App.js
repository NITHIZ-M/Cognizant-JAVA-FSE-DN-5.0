import { useState } from 'react'
import BookDetails from './BookDetails'
import BlogDetails from './BlogDetails'
import CourseDetails from './CourseDetails'

function App() {
  const [type, setType] = useState("book")

  let component
  if (type === "book") {
    component = <BookDetails />
  } else if (type === "blog") {
    component = <BlogDetails />
  } else {
    component = <CourseDetails />
  }

  return (
    <div>
      <button onClick={() => setType("book")}>Book</button>
      <button onClick={() => setType("blog")}>Blog</button>
      <button onClick={() => setType("course")}>Course</button>

      {type === "book" && <BookDetails />}
      {type === "blog" ? <BlogDetails /> : null}
      {type === "course" && <CourseDetails />}

      {component}
    </div>
  )
}

export default App;
