(ns clojure-course-task02.core
  (:gen-class))

(import java.io.File)

(defn find-files [file-name path]
  (defn get-files [f]
    (if (.isFile f)
      (if (re-matches (re-pattern file-name) (.getName f)) [(.getName f)] [])
      (reduce concat (doall (pmap get-files (.listFiles f))))))
  (get-files (File. path)))

(defn usage []
  (println "Usage: $ run.sh file_name path"))

(defn -main [file-name path]
  (if (or (nil? file-name)
          (nil? path))
    (usage)
    (do
      (println "Searching for " file-name " in " path "...")
      (dorun (map println (find-files file-name path)))))
  (shutdown-agents))