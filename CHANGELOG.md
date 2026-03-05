# Changelog - V.2.0 - 2026/03/04 - 08:00 

### Added
- Multi trial experiment runs for more accurate data.
- Randomized UI elements placements.
- Misclick tracking.
- Ability to display total and average (still needs to be coded in) time for each dp target.
- Result screen displaying
  - Total time taken to complete each touch target value.
  - Number of runs performed for each touch target value.
  - Number of misclicks.
  - Average time taken to complete a set.
- Navigation button to Home. 

### Changed 
- Updated UI.
- Improved button visibility.
- Improved results screen (the previous one sucked).

### Fixed 
- Timing calculation bug in Task32Activity.

### Need to add  
- More interactive elements to increase complexity.
- More target sizes beyond just the two already implemented. 
- Transition Screen
  - Prevents fatigue which may affect the quality of data recorded. 
  - Informs user so that they're better prepared for the next target size. 
- Data collection improvements 
  - Record per-trial completion times instead of only totals.
  - More points of data analysis, specifically user error rate.
  

# Changelog V0.2.1 - 2026/03/04 - 02:10 

### Added 
- Trial counter HUD showing current condition (dp size) and trial number. 
- Randomized placement constrained to be more in the centre to avoid overlap with the notch/punch-hole on some devices. 
- Transition screen between conditions showing previous run summary and a brief overview of what's about to come next.

### Fixed 
- Prevented targets spawning too close to the status bar and the trial counter HUD. 
- Some UI changes.

### Need to add 
- More target sizes. 
- A potential overhaul of logic handling to avoid code duplication. 
- Data collection improvements mentioned in v0.2.0. 



# CHANGELOG v0.3.0 - 2026/03/04 - 6:17 

### Added 
- Array based results storage.
- Dynamic sorted result rendering based on the number of target sizes.
- Improved trial counter HUD overlay.
- Added 3 more target sizes.
- Logic handling overhaul, it is now much more scalable with new touch target sizes. 
- Improved results page. t

### Removed 
- Deprecated hardcoded result variables needing code duplication to scale. 
- Deprecated activity structure based tied to specific touch target sizes. 

### Planned 
- Data collection improvements
