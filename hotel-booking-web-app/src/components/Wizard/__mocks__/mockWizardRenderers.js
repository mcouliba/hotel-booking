import React from 'react';
import MockWizardDeployContents from './mockWizardDeployContents';
import { Wizard } from 'patternfly-react';
import { mockWizardFormContents, wizardRoomSelectedContents } from './mockWizardItems';
import MockWizardReviewStepsManager from './mockWizardReviewStepsManager';

export const renderWizardSteps = (
  wizardSteps,
  activeStepIndex,
  activeSubStepIndex,
  onStepClick
) => {
  const activeStep = wizardSteps[activeStepIndex];
  const activeSubStep = activeStep.subSteps[activeSubStepIndex];

  return wizardSteps.map((step, stepIndex) => (
    <Wizard.Step
      key={stepIndex}
      stepIndex={stepIndex}
      step={step.step}
      label={step.label}
      title={step.title}
      activeStep={activeStep && activeStep.step}
      onClick={onStepClick}
    >
      {step.subSteps.map((sub, subStepIndex) => (
        <Wizard.SubStep
          key={subStepIndex}
          subStep={sub.subStep}
          title={sub.title}
          activeSubStep={activeSubStep && activeSubStep.subStep}
        />
      ))}
    </Wizard.Step>
  ));
};

export const renderSidebarItems = (
  wizardSteps,
  activeStepIndex,
  activeSubStepIndex,
  onSidebarItemClick
) => {
  const activeStep = wizardSteps[activeStepIndex];
  const activeSubStep = activeStep.subSteps[activeSubStepIndex];

  return wizardSteps.map((step, stepIndex) => (
    <Wizard.SidebarGroup
      key={stepIndex}
      step={step.step}
      activeStep={activeStep.step}
    >
      {step.subSteps.map((sub, subStepIndex) => (
        <Wizard.SidebarGroupItem
          key={subStepIndex}
          stepIndex={stepIndex}
          subStepIndex={subStepIndex}
          subStep={sub.subStep}
          label={sub.label}
          title={sub.title}
          activeSubStep={activeSubStep.subStep}
          onClick={onSidebarItemClick}
        />
      ))}
    </Wizard.SidebarGroup>
  ));
};

export const renderWizardContents = (
  wizardSteps,
  activeStepIndex,
  activeSubStepIndex,
  room,
  userid
) =>
  wizardSteps.map((step, stepIndex) =>
    step.subSteps.map((sub, subStepIndex) => {
      if (stepIndex === 0 || stepIndex === 1) {
        // render steps 1 and 2 mock contents
        return (
          <Wizard.Contents
            key={subStepIndex}
            stepIndex={stepIndex}
            subStepIndex={subStepIndex}
            activeStepIndex={activeStepIndex}
            activeSubStepIndex={activeSubStepIndex}
          >
            {(sub.subStep === '1.1') && wizardRoomSelectedContents(room)}
            {(sub.subStep !== '1.1') && mockWizardFormContents(sub.contents.label1, sub.contents.label2)}
          </Wizard.Contents>
        );
      } else if (stepIndex === 2 ) {
        // render mock summary
        return (
          <Wizard.Contents
            key={subStepIndex}
            stepIndex={stepIndex}
            subStepIndex={subStepIndex}
            activeStepIndex={activeStepIndex}
            activeSubStepIndex={activeSubStepIndex}
          >
            <MockWizardReviewStepsManager
                steps={wizardSteps}
                userid = {userid} />
          </Wizard.Contents>
        );
      } else if (stepIndex === 3) {
        // render mock progress
        return (
          <Wizard.Contents
            key={subStepIndex}
            stepIndex={stepIndex}
            subStepIndex={subStepIndex}
            activeStepIndex={activeStepIndex}
            activeSubStepIndex={activeSubStepIndex}
          >
            <MockWizardDeployContents
              active={
                stepIndex === activeStepIndex &&
                subStepIndex === activeSubStepIndex
              }
            />
          </Wizard.Contents>
        );
      }
      return null;
    })
  );
